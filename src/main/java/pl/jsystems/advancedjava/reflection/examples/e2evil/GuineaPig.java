package pl.jsystems.advancedjava.reflection.examples.e2evil;

class GuineaPig
{
    private final String name;
    private final int age;
    private final int weight;

    private boolean isHungry;
    private boolean isSleeping;

    private GuineaPig(String name, int weight)
    {
        this.name = name;
        this.age = 0;
        this.weight = weight;
    }

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
        return isHungry;
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
            isHungry = true;
        }
    }

    @Override
    public String toString()
    {
        return "GuineaPig{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", isHungry=" + isHungry +
                ", isSleeping=" + isSleeping +
                '}';
    }
}