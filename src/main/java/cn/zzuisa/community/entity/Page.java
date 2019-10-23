package cn.zzuisa.community.entity;

/**
 * @author Ao
 * @date 2019-10-24 01:03
 */
public class Page {
    // current Page
    private int current = 1;
    // the maxium pages
    private int limit = 10;
    // the limited data rows
    private int rows;
    // search path
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1)
            this.current = current;

    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 50)
            this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0)
            this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getOffset() {
        /**
         * @Author: Ao
         * @date: 24-10-2019 01:08
         * @Description:
         * @param
         * @return: int
         */
        return (current - 1) * limit;
    }

    public int getTotal() {
        /**
         * @Author: Ao
         * @date: 24-10-2019 01:09
         * @Description: get the overall pages
         * @param
         * @return: int
         */
        if (rows % limit == 0) {
            return rows / limit;
        }else
            return rows / limit + 1;
    }
    public int getFrom() {
        /**
         * @Author: Ao
         * @date: 24-10-2019 01:11
         * @Description: get the initial page
         * @param
         * @return: int
         */
        int from = current - 2;
        return from < 1 ? 1 : from;
    }
    public int getTo(){
        /**
         * @Author: Ao
         * @date: 24-10-2019 01:12
         * @Description: get the last page
         * @param
         * @return: int
         */
        int to = current+2;
        int total = getTotal();
        return to > total ? total : to;
    }

}
