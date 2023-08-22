/**************************************************************
 Purpose/Description: MinHeap Replacement
 Author’s Panther ID:  6187231
 Certification:
 I hereby certify that this work is my own and none of it is the work of
 any other person.
 **************************************************************/
import java.nio.BufferUnderflowException;
import java.util.*;
import java.util.Comparator;

public class MinHeap<AnyType>
{
    public MinHeap( )
    {
        this( DEFAULT_CAPACITY );
    }

    public MinHeap(int capacity )
    {
        this( null, capacity );
    }

    public MinHeap(Comparator<? super AnyType> c )
    {
        this( c, DEFAULT_CAPACITY );
    }

    public MinHeap(Comparator<? super AnyType> c, int capacity )
    {
        currentSize = 0;
        array = (AnyType[]) new Comparable[ capacity + 1 ];
        cmp = c;
    }

    public MinHeap(AnyType [ ] items )
    {
        this( items, null );
    }

    public MinHeap(AnyType [ ] items, Comparator<? super AnyType> c )
    {
        cmp = c;
        currentSize = items.length;
        array = (AnyType[]) new Object[ ( currentSize + 2 ) * 11 / 10 ];

        int i = 1;
        for( AnyType item : items )
            array[ i++ ] = item;
        buildHeap( );
    }

    private int myCompare( AnyType lhs, AnyType rhs )
    {
        if( cmp != null )
            return cmp.compare( lhs, rhs );
        else
            return ((Comparable)lhs).compareTo( rhs );
    }


    public void insert( AnyType x )
    {
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );

        // Percolate up
        int hole = ++currentSize;
        for( ; hole > 1 && myCompare( x, array[ hole / 2 ] ) < 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
    }


    private void enlargeArray( int newSize )
    {
        AnyType [] old = array;
        array = (AnyType []) new Object[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }

    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new BufferUnderflowException();
        return array[ 1 ];
    }

    public AnyType deleteMin( )
    {
        if( isEmpty( ) )
            throw new BufferUnderflowException();

        AnyType minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }

    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }

    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    public void makeEmpty( )
    {
        currentSize = 0;
    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;      // Number of elements in heap
    private AnyType [ ] array; // The heap array
    private Comparator<? super AnyType> cmp;


    private void percolateDown( int hole )
    {
        int child;
        AnyType tmp = array[ hole ];

        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;
            if( child != currentSize &&
                    myCompare( array[ child + 1 ], array[ child ] ) < 0 )
                child++;
            if( myCompare( array[ child ], tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }
    // replaceKey method implementation
    public void replaceKey(Integer oldKey, Integer newKey)
    {
        boolean found = false;// local variables
        for (int i = 1; i < currentSize; i++)// repeat the loop for all keys in the heap
        {
            if (oldKey.compareTo((Integer) array[i]) == 0)// verify whether the oldKey is found in the heap
            {
                found = true;// set found to true
                array[i] = (AnyType) newKey;// replace the oldKay by the newKey
                for (i = currentSize / 2; i > 0; i--){// rebuild the heap using the percolateDown method
                    percolateDown(i);
                }
                break;// exit from the loop
            }
        }
        if(found == false)
        {
            System.out.println(oldKey + " is not found in the heap.");// display an error message if the oldKey does not exist in the heap
        }
    } // end of replaceKey method

    // toString method implementation
    public String toString() {
        String result = "";
        for (int i = 1; i <= currentSize; i++)
        {
            result += array[i] + " ";
        }
        return result;
    } // end of toString method
    // main method implementation
    public static void main(String[] args) {

        Integer[] oldHeap = { 4, 6, 7, 32, 19, 64, 26, 99, 42, 54, 28 };// create an array of elements of type Integer
        MinHeap<Integer> bh = new MinHeap<Integer>(oldHeap);// create an object for MinHeap class
        System.out.println("Old Heap: *** " + bh);// display the keys in the old heap
        bh.replaceKey(54, 2);// call the replaceKey method
        System.out.println("New Heap: *** " + bh);// display the keys in the new heap
    } // end of main method
}