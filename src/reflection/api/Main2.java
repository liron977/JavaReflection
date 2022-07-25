package reflection.api;

public class Main2 {
    public static void main(String[] args) {

        Student tst=new Student("liron",3, new int[]{100, 59});
        InvestigateClass TEST=new InvestigateClass();
        Rectangle rectangle = new Rectangle(4,6);
       TEST.load(rectangle);
        //TEST.load(tst);
        System.out.println(TEST.getTotalNumberOfFields()+" fields ");
        System.out.println(TEST.getTotalNumberOfMethods());
        System.out.println(TEST.getCountOfConstantFields()+" const fields");
        System.out.println(TEST.getTotalNumberOfConstructors()+"constracor");
        System.out.println(TEST.getCountOfStaticMethods()+"static metho");
        System.out.println(TEST.isExtending()+" isExtending");
        System.out.println(TEST.getParentClassSimpleName()+" getParentClassSimpleName");
        System.out.println(TEST.isParentClassAbstract()+" isParentClassAbstract");
        System.out.println(TEST.isParentClassAbstract());
        System.out.println(TEST.getInheritanceChain("<<"));
        System.out.println(TEST.invokeMethodThatReturnsInt("twice",21)+"invokeMethodThatReturnsInt");
        System.out.println(TEST.createInstance(3,1,2,3));
        System.out.println(TEST.getAllImplementedInterfaces()+" getAllImplementedInterfaces");
        System.out.println(TEST.getNamesOfAllFieldsIncludingInheritanceChain()+" getNamesOfAllFieldsIncludingInheritanceChain");

        Class<Integer>[] parametersTypes = new Class[0];
        int res=(int)TEST.elevateMethodAndInvoke("twice",new Class<?>[]{int.class},2);
        System.out.println(res);


    }
}
