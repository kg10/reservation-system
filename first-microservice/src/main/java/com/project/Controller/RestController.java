package com.project.Controller;

import java.util.Date;
import java.util.List;

import com.project.Model.*;
import com.project.Model.Rest.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.project.Service.AdministratorService;
import com.project.Service.ClientService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/reg")
@Api(value = "Main microservice", description = "Operations for booking services")
@CrossOrigin
public class RestController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private ClientService clientService;


    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestParam String login) {
        try {
            Client client = clientService.findOneByLogin(login);

            if (client.getRole().equals("USER"))
                return new ResponseEntity<>(JSONObject.quote(client.getRole()), HttpStatus.OK);
            else if (client.getRole().equals("ADMIN"))
                return new ResponseEntity<>(JSONObject.quote(client.getRole()), HttpStatus.ACCEPTED);
            else
                throw new Exception();

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @ApiOperation(value = "Registation user")
    @PostMapping(value = "/addClient", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPerson(@RequestBody Client client) {
        try {
            administratorService.createClient(client);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getClient")
    public ResponseEntity<ClientResponse> getClient(@RequestParam String login) {
        try {
            return new ResponseEntity<>(clientService.findClient(login), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Customer deactivation")
    @PutMapping("/disableClient/{id}")
    public ResponseEntity<Void> disableClient(@PathVariable("id") Long id) {
        try {
            administratorService.disableClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateClient/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editClient(@PathVariable Long id, @RequestBody Client client) {
        try {
            clientService.updateClient(id, client);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Personel deactivation")
    @GetMapping("/disablePersonnel/{id}")
    public ResponseEntity<Void> disablePersonnel(@PathVariable("id") Long id) {
        try {
            administratorService.disablePersonnel(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addService", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addService(@RequestBody ServiceWrapper serviceWrapper) {
        try {
            administratorService.addPersonnelAndService(serviceWrapper);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addPersonnel", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addPersonnel(@RequestBody Personnel personnel) {
        try {
            return new ResponseEntity<>(administratorService.addPersonnel(personnel), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/assign/{idPerson}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> assignPersonToService(@PathVariable Long idPerson, @RequestBody AssignRequest listDscrService) {
        try {
            administratorService.assignPersonToService2(idPerson, listDscrService.getListDscrService());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/disableReservation/{idReservation}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> disableReservation(@PathVariable Long idReservation, @RequestBody Boolean status) {
        try {
            clientService.disableReserv(idReservation, status);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addTimeTable", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPersonnel(@RequestBody TimeTableRequest timeTableRequest) {
        try {
            administratorService.addTimeTableToPerson(timeTableRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findServiceByPersonnelId")
    public ResponseEntity<List<Service>> findServiceByLastName(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(clientService.findServices(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findAllServices")
    public ResponseEntity<List<Service>> findAllServices() {
        try {
            return new ResponseEntity<>(clientService.findAllServices(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findHistoryService")
    public ResponseEntity<List<HistoryReservation>> findHistoryService(@RequestParam String login) {
        try {
            return new ResponseEntity<>(clientService.getAllReservationByLogin(login), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findAllHistory")
    public ResponseEntity<List<HistoryResponse>> findAllHistory() {
        try {
            return new ResponseEntity<>(administratorService.getAllReservation(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findPersonnelByServiceId")
    public ResponseEntity<List<Personnel>> selectPersonnelByServiceName(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(clientService.findPersonnels(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findAllPersonnel")
    public ResponseEntity<List<Personnel>> selectPersonnel() {
        try {
            System.out.println("test");
            return new ResponseEntity<>(clientService.findAllPersonnel(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findTime")
    public ResponseEntity<TimeTable> checkTimeTable(@RequestParam Long id,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        try {
            return new ResponseEntity<>(clientService.findTimeTable(id, date), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findTimeByPerson")
    public ResponseEntity<List<TimeTableResponse>> getTimeByPerson(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(administratorService.getTimeByPersonelId(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addReservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReservation(@RequestBody ReservationRequest reservationRequest) {
        try {
            if (clientService.addReservation(reservationRequest))
                return new ResponseEntity<>(HttpStatus.CREATED);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/disableReservation/{id}")
    public ResponseEntity<Void> disableReservation(@RequestParam String serviceName, @PathVariable Long id,
                                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        try {
            clientService.disableReservation(serviceName, id, date);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getFreeTime")
    public ResponseEntity<List<String>> checkFreeTime(@RequestParam Long id,
                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam Long idService) {
        try {
            return new ResponseEntity<>(clientService.checkFreeTime(date, id, idService), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> registerNewAcount(@RequestBody Client client) {
        try {
            return new ResponseEntity<>(clientService.createNewAccount(client), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateService/{id}")
    public ResponseEntity<Void> updateService(@PathVariable Long id, @RequestBody Service service) {
        try {
            administratorService.setServiceById(id, service);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updatePersonnel/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable Long id, @RequestBody Personnel person) {
        try {
            administratorService.setPersonnelById(id, person);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        try {
            administratorService.deletePersonnel(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/deleteTime")
    public ResponseEntity<Void> deleteTime(@RequestParam Long id) {
        try {
            administratorService.deleteTimeTable(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
