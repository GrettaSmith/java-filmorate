package ru.yandex.practicum.filmorate.service;

import com.sun.source.tree.UsesTree;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.DuplicateException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserStorage userStorage;

    public List<User> getUsersList() {
        return userStorage.getAll();
    }

    public User addUser(User user) {
        if (user.getName() == null || user.getName().equals("")) {
            user.setName(user.getLogin());
        }
        return userStorage.create(user);
    }

    public User updateUser(User user) {
        if (user.getName() == null || user.getName().equals("")) {
            user.setName(user.getLogin());
        }
        return userStorage.update(user);
    }

    public User getUserById(Integer id) {
        return userStorage.get(id);
    }

    public List <User> getUsersFrendsList(Integer id) {
        User user = getUserById(id);
        List <User> friendList1 = null;
        for (Integer friendId : user.getFriends()) {
            friendList1 = new ArrayList<>((Collection) getUserById(friendId));
        }


        return friendList1;
    }

    public Set<User> getUsersCommonFriends(Integer id, Integer otherId) {
        User user0 = getUserById(id);
        User user1 = getUserById(otherId);
        Set<User> commonFriends = new HashSet<>();
        for (Integer friend : user0.getFriends()) {
            if (user1.getFriends().contains(friend)) {
                commonFriends.add(getUserById(friend));
            }
        }
        return commonFriends;
    }

    public User addFriend(Integer id, Integer friendId) throws DuplicateException {
        if (id == friendId) {
            throw new DuplicateException("User can't add own page to friends!");
        }
        User user = getUserById(id);
        User user1 = getUserById(friendId);
        user.getFriends().add(friendId);
        user1.getFriends().add(id);
        userStorage.update(user);
        userStorage.update(user1);
        return user;
    }

    public User deleteFriend(Integer id, Integer friendId) throws DuplicateException {
        User user = getUserById(id);
        User user1 = getUserById(friendId);
        if (!user.getFriends().contains(friendId) || !user1.getFriends().contains(id)) {
            throw new DuplicateException("Users not friends yet!");
        }
        user.getFriends().remove(friendId);
        user1.getFriends().remove(id);
        userStorage.update(user);
        userStorage.update(user1);
        return user;
    }

}