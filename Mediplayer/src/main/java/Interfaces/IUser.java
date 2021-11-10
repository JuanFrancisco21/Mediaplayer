package Interfaces;

import java.util.Set;

public interface IUser {
    Integer getId();

    String getName();

    String getPhoto();

    String getEmail();

    Set<IList> getCreated();

    Set<IList> getSubscribed();

    Set<IPlay> getReproductions();
}
