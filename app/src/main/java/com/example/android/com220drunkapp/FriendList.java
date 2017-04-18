package com.example.android.com220drunkapp;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Friedrich on 4/17/2017.
 */

public class FriendList
{
    List friends = new ArrayList<Friend>();

    //TODO make a new Activity that allows you to add or remove friends from the list.
    public FriendList()
    {

    }

    public void addFriend(Friend frand)
    {
        friends.add(frand);
    }

    public void removeFriend(int index)
    {
        friends.remove(index);
    }

    public String toJSON()
    {
        return new Gson().toJson(friends.toArray());
    }

    public void fromJSON(String str)
    {
        Friend[] friends1 = new Gson().fromJson(str, Friend[].class);
        friends = new ArrayList<Friend>();
        for (Friend frand : friends1) {
            friends.add(frand);
        }
    }

    public class Friend
    {
        public Friend()
        {

        }
    }
}
