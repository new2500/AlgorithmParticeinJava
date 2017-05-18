package airbnb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EricLee on 10/28/16.
 */
class NestedInteger {
    private List<NestedInteger> list;
    private Integer integer;

    public NestedInteger(List<NestedInteger> list){
        this.list = list;
    }

    public void add(NestedInteger nestedInteger) {
        if(this.list != null){
            this.list.add(nestedInteger);
        } else {
            this.list = new ArrayList();
            this.list.add(nestedInteger);
        }
    }

    public void setInteger(int num) {
        this.integer = num;
    }

    public NestedInteger(Integer integer){
        this.integer = integer;
    }

    public NestedInteger() {
        this.list = new ArrayList();
    }

    public boolean isInteger() {
        return integer != null;
    }

    public Integer getInteger() {
        return integer;
    }

    public List<NestedInteger> getList() {
        return list;
    }

    public String printNi(){
        if (this.isInteger()) {
            return String.valueOf(integer);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).printNi());
                if (i != list.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }
}
