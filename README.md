# ENSF409 ToolShop
ENSF 409 Final Project with Jay Jay Cayabyab, Navjot Brar and Tyler Lam.

Quick link to lab document: [LINK](https://d2l.ucalgary.ca/d2l/le/content/251154/viewContent/3442717/View).

## Code Style

- Use built-in auto-formatter for code.
- Leave Javadoc comments for all functions and variable names (with appropriate *@param* and *@return* tags).

## Git Conventions

- **Do not** directly commit to master. Instead, make a branch and submit a pull request with the changes.
- Branches should be named with the following convention: Name/feature-hyphen-separated. For example, JayJay/readme-updates.
- Branches should be deleted after they are merged to master.

Quick procedure for creating a pull request:

1. Commit all changes to a specified branch.
2. Do `git merge master` inside of the branch and resolve and commit any merge conflicts that may arise.
3. Create a pull request on Github.

## Client-Server Data Sending Conventions

The server should be listening at all times to see what the client is going to send. The server should receive some message to specify what kind of data is going to be sent. We should create an interface that contains constants for strings that specify what kind of data is sent. For example, look at the following prototype:

```java

public interface Constants {
  public static final String CREATE_TOOL = "CREATE_TOOL";
  public static final String DELETE_TOOL = "DELETE_TOOL";
  public static final String SEARCH_TOOL_ID = "SEARCH_TOOL_ID";
  public static final String SEARCH_TOOL_NAME = "SEARCH_TOOL_NAME";
  ...
}

```

This will make any sending errors less prone and caught in compilation rather than the program not working as intended.