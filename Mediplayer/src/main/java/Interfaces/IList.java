package Interfaces;

import java.util.Set;

public interface IList {
    String getName();

    Integer getId();

    String getDescription();

    IUser getCreator();

    Set<IUser> getSusbcribers();

    Set<ISong> getSongs();

}
