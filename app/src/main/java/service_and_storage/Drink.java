package service_and_storage;

import java.util.Objects;

public class Drink
{
    private String name;
    private double sizeInOz;
    private double proof;
    private long timeDrank;
    
    public Drink()
    {
        this.name = null;
        this.sizeInOz = Double.NaN;
        this.proof = Double.NaN;
        this.timeDrank = 0;
    }
    
    public Drink(String name, double sizeInOz, double proof, int timeDrank)
    {
        this.name = name;
        this.sizeInOz = sizeInOz;
        this.proof = proof;
        this.timeDrank = timeDrank;
    }

    public String getName()
    {
        return this.name;
    }

    public double getSizeInOz()
    {
        return this.sizeInOz;
    }

    public double getProof()
    {
        return this.proof;
    }
    
    public long getTimeDrank()
    {
        return this.timeDrank;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSizeInOz(double sizeInOz)
    {
        this.sizeInOz = sizeInOz;
    }

    public void setProof(double proof)
    {
        this.proof = proof;
    }
    
    public void setTimeDrank(long timeDrank)
    {
        this.timeDrank = timeDrank;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Drink otherDrink = (Drink) obj;
        return Objects.equals(this.name, otherDrink.name);
    }
}