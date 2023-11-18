package pl.jsystems.advancedjava.reflection.examples.e1class;

class GuineaPig
{
    private final String name;
    private final int age;
    private final int weight;

    private boolean hungry;
    private boolean isSleeping;

    GuineaPig(String name, int age, int weight)
    {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    String getName()
    {
        return name;
    }

    int getAge()
    {
        return age;
    }

    int getWeight()
    {
        return weight;
    }

    boolean isHungry()
    {
        return hungry;
    }

    boolean isSleeping()
    {
        return isSleeping;
    }

    void setSleeping(boolean sleeping)
    {
        isSleeping = sleeping;
        if (!sleeping)
        {
            hungry = true;
        }
    }
}