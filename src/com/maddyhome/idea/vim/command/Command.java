package com.maddyhome.idea.vim.command;

/*
 * IdeaVim - A Vim emulator plugin for IntelliJ Idea
 * Copyright (C) 2003 Rick Maddy
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

import com.intellij.openapi.actionSystem.AnAction;

/**
 * This represents a single Vim command to be executed. It may optionally include an argument if appropriate for
 * the command. The command has a count and a type.
 */
public class Command
{
    /** Represents commands that actually move the cursor and can be arguments to operators */
    public static final int MOTION = 1;
    /** Represents commands that insert new text into the editor */
    public static final int INSERT = 2;
    /** Represents commands that remove text from the editor */
    public static final int DELETE = 3;
    /** Represents commands that change text in the editor */
    public static final int CHANGE = 4;
    /** Represents commands that copy text in the editor */
    public static final int COPY = 5;
    /** Represents commands that paste text into the editor */
    public static final int PASTE = 6;
    public static final int RESET = 7;
    /** Represents commands that select the register */
    public static final int SELECT_REGISTER = 8;
    /** Represents other types of commands */
    public static final int OTHER_READONLY = 9;
    public static final int OTHER_WRITABLE = 10;

    public static boolean isReadOnlyType(int type)
    {
        boolean res = false;
        switch (type)
        {
            case MOTION:
            case COPY:
            case SELECT_REGISTER:
            case OTHER_READONLY:
                res = true;
        }

        return res;
    }

    /**
     * Creates a command that doesn't require an argument
     * @param count The number entered prior to the command (zero if no specific number)
     * @param action The action to be executed when the command is run
     * @param type The type of the command
     * @param flags Any custom flags specific to this command
     */
    public Command(int count, AnAction action, int type, int flags)
    {
        this(count, action, type, flags, null);
    }

    /**
     * Creates a command that requires an argument
     * @param count The number entered prior to the command (zero if no specific number)
     * @param action The action to be executed when the command is run
     * @param type The type of the command
     * @param flags Any custom flags specific to this command
     * @param arg The argument to this command
     */
    public Command(int count, AnAction action, int type, int flags, Argument arg)
    {
        this.count = count;
        this.action = action;
        this.type = type;
        this.flags = flags;
        this.argument = arg;
    }

    /**
     * Returns the command count. A zero count is returned as one since that is the default for most commands
     * @return The command count
     */
    public int getCount()
    {
        return count == 0 ? 1 : count;
    }

    /**
     * Updates the command count to the new value
     * @param count The new command count
     */
    public void setCount(int count)
    {
        this.count = count;
    }

    /**
     * Gets to actual count entered by the user, including zero if no count was specified. Some commands need to
     * know whether an actual count was specified or not.
     * @return The actual count entered by the user
     */
    public int getRawCount()
    {
        return count;
    }

    /**
     * Gets the command type
     * @return The command type
     */
    public int getType()
    {
        return type;
    }

    /**
     * Gets the flags associated with the command
     * @return The command flags
     */
    public int getFlags()
    {
        return flags;
    }

    /**
     * Sets new flags for the command
     * @param flags The new flags
     */
    public void setFlags(int flags)
    {
        this.flags = flags;
    }

    /**
     * Gets the action to execute when the command is run
     * @return The command's action
     */
    public AnAction getAction()
    {
        return action;
    }

    /**
     * Sets a new action for the command
     * @param action The new action
     */
    public void setAction(AnAction action)
    {
        this.action = action;
    }

    /**
     * Gets the command's argument, if any.
     * @return The command's argument, null if there isn't one
     */
    public Argument getArgument()
    {
        return argument;
    }

    /**
     * Sets the command's argument to the new value
     * @param argument The new argument, can be null to clear the argument
     */
    public void setArgument(Argument argument)
    {
        this.argument = argument;
    }

    private int count;
    private AnAction action;
    private int type;
    private int flags;
    private Argument argument;
}