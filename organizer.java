ublic class Organizer {
    private int organizerId;
    private String name;
    private String email;
    private String department;

    public Organizer(int organizerId, String name, String email, String department) {
        this.organizerId = organizerId;
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "ID: " + organizerId +
               ", Name: " + name +
               ", Email: " + email +
               ", Department: " + department;
    }
}