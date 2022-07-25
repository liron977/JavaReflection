package reflection.api;

import java.lang.reflect.*;
import java.util.HashSet;
import java.util.Set;

public class InvestigateClass implements Investigator {

   public Class clazz;
    private Object ObjectanInstanceOfSomething;
    public void load(Object anInstanceOfSomething)
    {
        ObjectanInstanceOfSomething=anInstanceOfSomething;
        clazz = anInstanceOfSomething.getClass();
    }
    public int getTotalNumberOfMethods()
    {
        Method[]  allMethods=clazz.getDeclaredMethods();
        return allMethods.length;
    }
    public int getTotalNumberOfConstructors()
    {
        Constructor[] allConstructors =clazz.getDeclaredConstructors();
        return  allConstructors.length;
    }
    public int getTotalNumberOfFields()
    {
        Field[] allFields=clazz.getDeclaredFields();
        return allFields.length;
    }
    @Override
     public Set<String> getAllImplementedInterfaces()
    {
        Set<String> set = new HashSet<String>();
        Class[] interfaces = clazz.getInterfaces();
        for(int i=0;i<interfaces.length;i++)
        {
           set.add(interfaces[i].getSimpleName());
        }
        return set;

    }
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
    public boolean isExtending() {
      if(clazz.getSuperclass().equals(Object.class)||(clazz.equals(Object.class)))
      {
          return false;
      }

        return true;
    }
    @Override
    public String getParentClassSimpleName() {
        if (this.isExtending()) {
            return clazz.getSuperclass().getSimpleName();
        }
        return null;
    }
    @Override
    public boolean isParentClassAbstract() {
        if(!this.isExtending())
        {
            return false;
        }
        else if(Modifier.isAbstract( clazz.getSuperclass().getModifiers()))
        {
            return true;
        }
        return false;
    }


    /**
     * get all the names of all fields, including the ones coming from the inheritance chain
     * all fields of any type should exists: private, public, protected, static etc.
     *
     * @return set of fields names
     */
    @Override
    public Set<String> getNamesOfAllFieldsIncludingInheritanceChain() {
        Set<String> set=new HashSet<String>();
        Field[] allFields;
        Class tmpClazz= clazz;
        while (tmpClazz != null)
        {
            allFields=tmpClazz.getDeclaredFields();
            for (int i=0;i<allFields.length;i++)
            {
                set.add(allFields[i].getName());
            }
           tmpClazz=tmpClazz.getSuperclass();
        }
        return set;
    }
    @Override
    public int invokeMethodThatReturnsInt(String methodName, Object... args) {
        int result=0;
        Method[]  allMethods=clazz.getDeclaredMethods();
        for(int i=0;i<allMethods.length;i++)
        {
            if (allMethods[i].getName().equals(methodName))
            {
                try {
                    result = (int) allMethods[i].invoke(ObjectanInstanceOfSomething, args);
                    break;
                }
                   catch(Exception e) {}
            }
        }
        return result;
    }
    @Override
    public Object createInstance(int numberOfArgs, Object... args)
    {
        Constructor[] allConstructors =clazz.getDeclaredConstructors();
        Object newObject=null;
        for(int i=0;i<allConstructors.length;i++)
        {
            if(allConstructors[i].getParameterCount()==numberOfArgs)
            {
                try {
                    newObject=allConstructors[i].newInstance(args);
                    break;
                }
                catch (Exception e) {}

            }
        }
        return newObject;
    }
    @Override
    public Object elevateMethodAndInvoke(String name, Class<?>[] parametersTypes, Object... args) {
        Object resultOfInvocation=null;
        try {
            Method method = clazz.getDeclaredMethod(name,parametersTypes);
            method.setAccessible(true);
            resultOfInvocation=method.invoke(ObjectanInstanceOfSomething,args);
        }
        catch (Exception e){}

        return resultOfInvocation;
    }
    @Override
    public String getInheritanceChain(String delimiter) {
        String strResult="";
       Class tmpClazz= clazz;
        while (tmpClazz != null)
        {
            strResult= tmpClazz.getSimpleName()+strResult;
           if(!(tmpClazz.equals(Object.class)))
           {
               strResult= delimiter+strResult;
           }
           tmpClazz=tmpClazz.getSuperclass();
        }

        return strResult;

    }

}
