package me.ibe3esh.utils;

public interface ICommand
{
    void execute(final ExecuteArgs p0);

    String getName();

    String helpMessage();

    boolean needOwner();
}
