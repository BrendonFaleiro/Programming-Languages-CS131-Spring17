class C{
    public void m(){ System.out.println("Class C");}
    
    public void checker(String s){System.out.println("C's string checker");}
    public void checker(int i){System.out.println("C's int checker");}
}

class D extends C{
    public void m(){ System.out.println("Class D");}
    
    public void n(){ System.out.println("Class D n");}
    
    public void checker(int i){System.out.println("D's int checker");}
    public void checker(boolean f){System.out.println("D's bool checker");}
    
}

class Main{
      
    public static void main(String[] args){
        C c1 = new C();
        c1.m();
        c1.checker(5);
        c1.checker("hello");
        //c1.checker(false);
        
        
        C c2 = new D();
        c2.m();
        //c2.n();
        c2.m();
        c2.checker(5);
        c2.checker("hello");
        //c2.checker(false);
        
        //D d1 = new C();
        
        D d2 = new D();
        d2.m();
        d2.n();
        d2.m();
        d2.checker(5);
        d2.checker("hello");
        d2.checker(false);
    }
}