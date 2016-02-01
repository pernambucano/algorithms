public class QuickUnion {

    private int[] array;

    public QuickUnion(int size){
        array = new int[size];

        for (int i=0; i<size; i++){
            array[i] = i;
        }
    }

    public void union(int p, int q){
        int parentP = findRoot(p);
        int parentQ = findRoot(q);

        array[p] = array[parentQ];
    }

    public int findRoot(int p){
        while(array[p] != p){
            p = array[p];
        }
        return p;
    }

    public boolean connected(int p, int q){
        return (findRoot(p) == findRoot(q));
    }

    public int[] getArray(){
        return this.array;
    }

    public static void main(String[] args){
        int N = StdIn.readInt();
        QuickUnion quickUnion = new QuickUnion(N);
        int[] myArray = quickUnion.getArray();
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if(!quickUnion.connected(p,q)){
                quickUnion.union(p,q);
                for (int i =0; i<myArray.length; i++){
                    StdOut.print(myArray[i]);
                }
            }
        }
    }
}
