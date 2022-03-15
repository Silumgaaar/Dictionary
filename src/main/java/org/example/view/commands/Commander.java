package org.example.view.commands;

import org.example.view.Commands;

public interface Commander {
    Commands getInfo();
    void execute();
}
