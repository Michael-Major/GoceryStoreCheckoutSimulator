package Project4;
//THIS CODE WAS TAKEN FROM PROFESSOR DOVOLIS AND MODIFIED FOR THE PURPOSE OF PROJECT4 OF CSCI 1933 (SPRING 2015)
// Queue Interface

public interface Q {

// Interface for a Queue

    void add(Object o);

    /* adds an object o to a queue placing it in the order of arrival 
       relative to other items added to the queue--first in, first out
       (FIFO) */

    Object remove();
 
    /* removes and returns the object placed in a queue prior
       to any other items presently in the queue */

    int length();

    /* returns the integer quantity of items currently present in the 
       queue */ 

}  // Q Interface
