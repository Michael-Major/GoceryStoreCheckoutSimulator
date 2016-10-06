package Project4;
//THIS CODE WAS TAKEN FROM PROFESSOR DOVOLIS AND MODIFIED FOR THE PURPOSE OF PROJECT4 OF CSCI 1933 (SPRING 2015)
// N.java
// A *simplified* node class for use with the Stack1 class 
// and other uses as desired
// Posted previously, but used for simulation

public class N {
  
    // constructors
    
    public N() {}

    public N(Object o, N link) {
        data = o;
        next = link;
    }

    // selectors

    public Object getData() {
        return data;
    }

    public void setData(Object o) {
        data = o;
    }

    public N getNext() {
        return next;
    }

    public void setNext(N link) {
        next = link;
    }

    // instance variables

    private Object data;
    private N next;

}  // N class
