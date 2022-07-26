package reflection.api;

import java.lang.reflect.*;
import java.util.HashSet;
import java.util.Set;

public class InvestigateClass implements Investigator {
   public Class clazz;
    private Object ObjectanInstanceOfSomething;
    public void load(Object anInstanceOfSomething)
    {
        clazz = anInstanceOfSomething.getClass();
        ObjectanInstanceOfSomething=anInstanceOfSomething;
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
        for (Class anInterface : interfaces) {
            set.add(anInterface.getSimpleName());
        }
        return set;

    }
    public int getCountOfConstantFields() {
        int countOfConstantFields=0;
        Field[] allFields = clazz.getDeclaredFields();
        for (Field field : allFields) {
            if (Modifier.isFinal(field.getModifiers())) {
                countOfConstantFields++;
            }
        }
        return  countOfConstantFields;
    }
    public int getCountOfStaticMethods()
    {
        int countOfStaticMethods=0;
        Method[]  allMethods=clazz.getDeclaredMethods();
        for (Method method : allMethods) {
            if (Modifier.isStatic(method.getModifiers())) {
                countOfStaticMethods++;
            }
        }
        return  countOfStaticMethods;
    }
    @Override
    public boolean isExtending() {
        boolean ans=true;
      if(clazz.getSuperclass().equals(Object.class)||(clazz.equals(Object.class)))
      {
          ans= false;
      }
        return ans;
    }

    @Override
  public String getParentClassSimpleName() {

         if((clazz.equals(null))||(clazz.equals(Object.class))||(clazz.getSuperclass().equals(null)))
         {
             return null;
         }
        return clazz.getSuperclass().getSimpleName();

    }
    @Override
    public boolean isParentClassAbstract() {
       boolean result=false;
        if(clazz.equals(Object.class))
        {
            result=false;
        }
        else if(Modifier.isAbstract( clazz.getSuperclass().getModifiers()))
        {
            result= true;
        }
        return result;
    }
    @Override
    public Set<String> getNamesOfAllFieldsIncludingInheritanceChain() {
        Set<String> set=new HashSet<String>();
        Field[] allFields;
        Class tmpClazz= clazz;
        while (tmpClazz != null)
        {
            allFields=tmpClazz.getDeclaredFields();
            for (Field field : allFields) {
                set.add(field.getName());
            }
           tmpClazz=tmpClazz.getSuperclass();
        }
        return set;
    }
    @Override
    public int invokeMethodThatReturnsInt(String methodName, Object... args) {
        int result=0;
        Method[] allMethods=clazz.getDeclaredMethods();
        for (Method method : allMethods) {
            if (method.getName().equals(methodName)) {
                try {
                    result = (int) method.invoke(ObjectanInstanceOfSomething, args);
                    break;
                }
                catch (Exception e)
                {}
            }
        }
        return result;
    }
    @Override
    public Object createInstance(int numberOfArgs, Object... args)
    {
        Constructor[] allConstructors =clazz.getDeclaredConstructors();
        Object newObject=null;
        for (Constructor constructor : allConstructors) {
            try {
                if (constructor.getParameterCount() == numberOfArgs) {
                    if (numberOfArgs == 0)
                    {
                        newObject = constructor.newInstance();
                        break;

                    }
                    else
                    {
                        newObject = constructor.newInstance(args);
                        break;
                    }
                }
            }
                catch (Exception e) {}

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
        catch (Exception e)
        {}

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
