package com.heartsuit.showcase.domain.author;

/**
 * The type Author.作者
 * @author Administrator
 */
public class Author {
    private AuthorType authorType;

    /**
     * Gets author type.
     *
     * @return the author type
     */
    public AuthorType getAuthorType() {
        return authorType;
    }

    /**
     * Sets author type.
     *
     * @param authorType the author type
     */
    public void setAuthorType(AuthorType authorType) {
        this.authorType = authorType;
    }
}
