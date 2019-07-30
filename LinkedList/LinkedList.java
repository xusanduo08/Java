package com.mengfansheng.collection;

/**
 * 双向链表
 *  add(Object o);
 *  size();
 *  get(int index);
 *  remove(int index);
 *  add(int index, Object o);
 */
public class LinkedList {
    private Node first;
    private Node last;
    private int size;

    public void add(Object o){
        Node n = new Node();
        if(first == null){
            n.setPrevious(null);
            n.setNext(null);
            n.setValue(o);

			// 只有一个节点时，这个节点既是头节点也是尾节点
            first = n;
            last = n;
        } else {
            n.setPrevious(last);
            n.setNext(null);
            n.setValue(o);

            last.setNext(n);
            last = n;
        }

        size++;
    }

    public int size(){
        return size;
    }

    public Object get(int index){
		Node tmp = node(index);
        if(tmp != null){
            return tmp.getValue();
        } else {
            return null;
        }
    }

    public void remove(int index){
        checkIndex(index);
        if(first != null){
            Node tmp = node(index);
            Node previous = tmp.getPrevious();
            Node next = tmp.getNext();
            if(previous != null){ // 考虑删除第一个节点
                previous.setNext(next);
            } else {
                first = next;
            }
            if(next != null){ // 考虑删除最后一个节点
                next.setPrevious(previous);
            } else {
                last = previous;
            }
            size--;
        }
    }

    /*
     * 指定索引处添加节点
     */
    public void add(int index, Object v){
        checkIndex(index);
        Node n = new Node();
        n.setValue(v);
        Node tmp = node(index);
        if(tmp != null){
            Node previous = tmp.getPrevious();
            n.setPrevious(previous);
            n.setNext(tmp);
            tmp.setPrevious(n);
            if(previous != null){
                previous.setNext(n);
            } else { // 考虑add(0, v);
                first = n;
            }

        }

        size++;
    }

    /*
       获取指定索引处的节点
     */
    private Node node(int index){
		checkIndex(index);
        Node tmp = null;
		if(first != null){
			if(index < (size >> 1)){ // 右移一位（除以2），在前半段，从前往后找
				tmp = first;
				for(int i = 0; i < index; i++){
					tmp = tmp.getNext()
				}
			}
		} else { // 在后半段，从后往前找
			tmp = last;
			for(int i = size; i > index; i++){
				tmp = tmp.getPrevious();
			}
		}
        
        return tmp;
    }

    private void checkIndex(int index){
        if(index < 0 || index >= size){
            try{
                throw new Error("index out of bounds");
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        LinkedList list = new LinkedList();
        list.add("123");
        list.add(0,"321");
        list.remove(1);
        System.out.println(list.get(0));
    }
}

class Node {
    private Node previous;
    private Node next;
    private Object value;

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
