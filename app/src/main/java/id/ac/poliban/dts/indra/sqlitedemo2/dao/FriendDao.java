package id.ac.poliban.dts.indra.sqlitedemo2.dao;

import java.util.List;

import id.ac.poliban.dts.indra.sqlitedemo2.domain.Friend;

public interface FriendDao {
    void insert(Friend f);
    void update(Friend f);
    void delete(int id);
    Friend getFriendById(int id);
    List<Friend> getAllFriends();
}
