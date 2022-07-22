package reflection.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

public class InvestigateClass implements Investigator {

    private Class clazz;
    public void load(Object anInstanceOfSomething)
    {
        clazz = anInstanceOfSomething.getClass();
    }
    public int getTotalNumberOfMethods()
    {
        Method[]  allMethods=clazz.getDeclaredMethods();
        return allMethods.length;
    }
    public int getTotalNumberOfConstructors()
    {
        Constructor[] allConstructors =clazz.getConstructors();
        return  allConstructors.length;
    }
    public int getTotalNumberOfFields()
    {
        Field[] allFields=clazz.getDeclaredFields();
        return allFields.length;
    }

    @Override
     public Set<String> getAllImplementedInterfaces() {
        return null;
    }

    /**
     * returns the names of all the interfaces that this class implements
     * (and NOT the ones that are being implemented by it's ancestor in the inheritance chain)
     * Note that by name I mean simple, short Name that is only the interface name,
     * and not its fully qualified name including the package it is located within...
     *
     * @return set of interfaces names
     */
/*    Set<String> getAllImplementedInterfaces()
    {
        Class[] interfaces = clazz.getInterfaces();
        for(int i=0;i<interfaces.length;i++)
        {
            return interfaces[i].getSimpleName();
        }

    }*/
    public int getCountOfConstantFields() {
        int countOfConstantFields=0;
        Field[] allFields = clazz.getDeclaredFields();
        for (int i = 0; i < allFields.length; i++) {
            if (Modifier.isFinal(allFields[i].getModifiers())) {
                countOfConstantFields++;
            }
        }
        return  countOfConstantFields;
    }
    public int getCountOfStaticMethods()
    {
        int countOfStaticMethods=0;
        Method[]  allMethods=clazz.getDeclaredMethods();
        for (int i = 0; i < allMethods.length; i++) {
            if (Modifier.isStatic(allMethods[i].getModifiers())) {
                countOfStaticMethods++;
            }
        }
        return  countOfStaticMethods;
    }

    @Override
    /**
     * checks if the given class extends a super class, that is not Object (which all extend by default, implicitly)
     *
     * @return true if the class extends, false otherwise
     */
    public boolean isExtending() {
      if(clazz.getSuperclass().equals(Object.class)||(clazz.equals(Object.class)))
      {
          return false;
      }

        return true;
    }
    /**
     * get the name of the direct parent class
     * the name of the class is the short, simple name and not the fully qualified name
     *
     * @return the name of the parent class. null if this class doesn't extend other class
     */
    @Override
    public String getParentClassSimpleName() {
        if(this.isExtending()) {
            return clazz.getSuperclass().getSimpleName();
        }
        return null;
    }
    /**
     * checks if the given class parent is of type abstract
     *
     * @return true if the parent class is abstract, false otherwise (also in case the given class does not extends any other class)
     */
    @Override
    public boolean isParentClassAbstract() {
        if(this.isExtending())
        {
            return false;
        }
        else if(Modifier.isAbstract( clazz.getSuperclass().getModifiers()))
        {
            return true;
        }
        return false;
    }

    @Override
    public Set<String> getNamesOfAllFieldsIncludingInheritanceChain() {
        return null;
    }

    /**
     * creates a new instance of the given class, using a specific constructor,
     * to be determined by the number of given arguments.
     * No worries: there will be no ambiguity, i.e. two constructors that gets in 2 arguments each one of them...
     * This method should act as a standalone and without any side effects, that is, the newly created
     * instance SHOULD NOT replace the original given instance.
     * @param //numberOfArgs number of arguments that a specific constructor has. can be 0.
     * @param args arguments to pass to the constructor
     *
     * @return the newly created instance
     */
    @Override
    public int invokeMethodThatReturnsInt(String methodName, Object... args) {
        return 0;
    }

    @Override
    public Object createInstance(int numberOfArgs, Object... args) {
        return null;
    }

    @Override
    public Object elevateMethodAndInvoke(String name, Class<?>[] parametersTypes, Object... args) {
        return null;
    }

    @Override
    public String getInheritanceChain(String delimiter) {
        return null;
    }
    /**
     * checks if the given class extends a super class, that is not Object (which all extend by default, implicitly)
     *
     * @return true if the class extends, false otherwise
     */
 /*   boolean isExtending()
    {

    }*/


}
