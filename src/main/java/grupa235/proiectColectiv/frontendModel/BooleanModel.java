package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;

public class BooleanModel implements Serializable {
    private Boolean status;

    public BooleanModel() {
    }

    public BooleanModel(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
