package life.memy.identity.scim;

public class ScimUser {
	private Name name;

    private String userName;

    private Emails[] emails;

    private String password;

    public Name getName ()
    {
        return name;
    }

    public void setName (Name name)
    {
        this.name = name;
    }

    public String getUserName ()
    {
        return userName;
    }

    public void setUserName (String userName)
    {
        this.userName = userName;
    }

    public Emails[] getEmails ()
    {
        return emails;
    }

    public void setEmails (Emails[] emails)
    {
        this.emails = emails;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", userName = "+userName+", emails = "+emails+", password = "+password+"]";
    }

}
