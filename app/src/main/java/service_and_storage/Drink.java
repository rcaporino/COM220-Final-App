package service_and_storage;

import java.io.Serializable;

import java.util.Objects;

public class Drink implements Serializable
{
    private static final long BASE_TIME_DRANK = 0;
    
    private String name;
    private double sizeInOz;
    private double proof;
    private long timeDrank;
    
    public Drink()
    {
        this.name = null;
        this.sizeInOz = Double.NaN;
        this.proof = Double.NaN;
        this.timeDrank = Drink.BASE_TIME_DRANK;
    }
    
    public Drink(String name, double sizeInOz, double proof)
    {
        this.name = name;
        this.sizeInOz = sizeInOz;
        this.proof = proof;
        this.timeDrank = Drink.BASE_TIME_DRANK;
    }
    
    public Drink(String name, double sizeInOz, double proof, long timeDrank)
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

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
    @Override
    public String toString()
    {
        return this.getName();
    }
}