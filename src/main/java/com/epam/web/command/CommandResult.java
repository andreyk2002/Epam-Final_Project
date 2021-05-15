package com.epam.web.command;

import java.util.Objects;

public class CommandResult {

    private final boolean isRedirect;
    private final String page;

    public CommandResult(boolean isRedirect, String page) {
        this.isRedirect = isRedirect;
        this.page = page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public String getPage() {
        return page;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(false, page);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(true, page);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandResult)){
            return false;
        }
        CommandResult result = (CommandResult) o;
        if (isRedirect != result.isRedirect){
            return false;
        }
        return Objects.equals(page, result.page);
    }

    @Override
    public int hashCode() {
        int result = (isRedirect ? 1 : 0);
        result = 31 * result + (page != null ? page.hashCode() : 0);
        return result;
    }
}
