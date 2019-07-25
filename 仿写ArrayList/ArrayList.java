package com.mengfansheng.collection;

public class ArrayList {
    private Object[] elementData;
    private int size;

    public ArrayList(){
        this(10);
    }
    public ArrayList(int initialCapacity) {
        if(initialCapacity < 0){
            try {
                throw new Exception("initialCapacity cannot below to 0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.elementData = new Object[initialCapacity];
    }

    public void add(Object obj){
        //ensure capacity
        if(size >= elementData.length){
            Object[] tmp = new Object[size * 2];
            System.arraycopy(elementData, 0, tmp, 0, elementData.length);
            elementData = tmp;
        }
        elementData[size++] = obj;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Object get(int index){
        rangeCheck(index);
        return elementData[index];
    }

    public void remove(int index){

        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
    }

    private void rangeCheck(int index){
        if(index + 1 > size || index < 0) {
            try {
                throw new Exception("out of index");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        ArrayList list = new ArrayList(2);
        list.add("2222");
        list.add("asdf");
        list.add("jjj");
        System.out.println(list.get(3));
    }
}
