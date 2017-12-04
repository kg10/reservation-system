package com.project.Model.Rest;

import java.util.List;

public class AssignRequest {
    private Long idPerson;
    private List<String> listDscrService;

    public AssignRequest() {
    }

    public AssignRequest(Long idPerson, List<String> listIdService) {
        this.idPerson = idPerson;
        this.listDscrService = listIdService;
    }

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public List<String> getListDscrService() {
        return listDscrService;
    }

    public void setListDscrService(List<String> listDscrService) {
        this.listDscrService = listDscrService;
    }
}
