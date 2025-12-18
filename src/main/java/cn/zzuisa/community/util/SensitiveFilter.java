package cn.zzuisa.community.util;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    // Replacement token
    private static final String REPLACEMENT = ":)";

    // Root node
    private TrieNode rootNode = new TrieNode();

    @PostConstruct
    public void init() {
        try (
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        ) {
            String keyword;
            while ((keyword = reader.readLine()) != null) {
                // Add to the trie
                this.addKeyword(keyword);
            }
        } catch (IOException e) {
            logger.error("Failed to load sensitive words file: " + e.getMessage());
        }
    }

    // Add one sensitive word into the trie
    private void addKeyword(String keyword) {
        TrieNode tempNode = rootNode;
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            TrieNode subNode = tempNode.getSubNode(c);

            if (subNode == null) {
                // Initialize a child node
                subNode = new TrieNode();
                tempNode.addSubNode(c, subNode);
            }

            // Move to the child node and continue
            tempNode = subNode;

            // Mark the end of the keyword
            if (i == keyword.length() - 1) {
                tempNode.setKeywordEnd(true);
            }
        }
    }

    /**
     * Filter sensitive words.
     *
     * @param text the text to be filtered
     * @return filtered text
     */
    public String filter(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }

        // Pointer 1
        TrieNode tempNode = rootNode;
        // Pointer 2
        int begin = 0;
        // Pointer 3
        int position = 0;
        // Result
        StringBuilder sb = new StringBuilder();

        while (position < text.length()) {
            char c = text.charAt(position);

            // Skip symbols
            if (isSymbol(c)) {
                // If pointer 1 is at the root node, append this symbol to the result and move pointer 2 forward.
                if (tempNode == rootNode) {
                    sb.append(c);
                    begin++;
                }
                // Whether the symbol is at the beginning or in the middle, pointer 3 always moves forward.
                position++;
                continue;
            }

            // Check the next-level node
            tempNode = tempNode.getSubNode(c);
            if (tempNode == null) {
                // The substring starting at 'begin' is not a sensitive word.
                sb.append(text.charAt(begin));
                // Move to the next position
                position = ++begin;
                // Reset to root
                tempNode = rootNode;
            } else if (tempNode.isKeywordEnd()) {
                // A sensitive word is found; replace the substring from begin to position.
                sb.append(REPLACEMENT);
                // Move to the next position
                begin = ++position;
                // Reset to root
                tempNode = rootNode;
            } else {
                // Check the next character
                position++;
            }
        }

        // Append the remaining characters
        sb.append(text.substring(begin));

        return sb.toString();
    }

    // Determine whether a character is a symbol
    private boolean isSymbol(Character c) {
        // 0x2E80~0x9FFF is the East Asian character range.
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }

    // Trie
    private class TrieNode {

        // End-of-keyword marker
        private boolean isKeywordEnd = false;

        // Child nodes (key: next character, value: next node)
        private Map<Character, TrieNode> subNodes = new HashMap<>();

        public boolean isKeywordEnd() {
            return isKeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            isKeywordEnd = keywordEnd;
        }

        // Add a child node
        public void addSubNode(Character c, TrieNode node) {
            subNodes.put(c, node);
        }

        // Get a child node
        public TrieNode getSubNode(Character c) {
            return subNodes.get(c);
        }

    }

}
