package upm.gidea.constants;

/**
 *
 * @author elts
 */
public enum IdeaStatus {
    CREATED,
    WAITING_FOR_APPROVAL,
    REJECTED,
    PUBLISHED,;

    @Override
    public String toString() {
        return name();
    }
}
