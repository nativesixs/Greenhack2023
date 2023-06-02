package maas.maas.controllers;

public class Object {
        private long id;

        public Object(long id) {
            this.id = id;
        }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
        public String toString() {
            return String.format("Customer[id=%d]", id);
        }
}
