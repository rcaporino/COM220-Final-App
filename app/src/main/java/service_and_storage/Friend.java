package service_and_storage;

import java.io.Serializable;

    public class Friend implements Serializable
    {
        private String name;
        private String num;
        
        public Friend()
        {
            this.name = null;
            this.num = null;
        }
        
        public Friend(String name, String num)
        {
            this.name = name;
            this.num = num;
        }

        public String getName()
        {
            return name;
        }
        
        public String getNum()
        {
            return num;
        }
        
        public void setName(String name)
        {
            this.name = name;
        }

        public void setNum(String num)
        {
            this.num = num;
        }

        @Override
        public String toString()
        {
            return this.name + ": " + this.num;
        }
    }
