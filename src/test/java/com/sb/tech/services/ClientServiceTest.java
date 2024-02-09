package com.sb.tech.services;

import com.sb.tech.exceptions.NotFoundException;
import com.sb.tech.exceptions.UuidParseException;
import com.sb.tech.models.ClientModel;
import com.sb.tech.repositories.ClientRepository;
import com.sb.tech.repositories.TechnicianRepository;
import com.sb.tech.services.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    private static final UUID CLIENT_UUID = UUID.randomUUID();
    private static final String WRONG_UUID = "123";
    private static final String CLIENT_DOCUMENT = "123.456.789.00";
    private ClientModel clientModel;
    private List<ClientModel> clientsList;

    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        startClient();
        startListClients();
    }

    @Test
    void whenGetByUuidReturnsAClient(){
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(clientModel));
        ClientModel result = clientService.getByUuid(CLIENT_UUID.toString());
        assertNotNull(result);
        assertEquals(ClientModel.class, result.getClass());
    }

    @Test
    void whenGetBuUuidThrowsANotFoundException(){
        when(clientRepository.findById(any(UUID.class))).thenThrow(new NotFoundException(null));
        try{
            clientService.getByUuid(CLIENT_UUID.toString());
        }catch (RuntimeException ex){
            assertEquals(NotFoundException.class, ex.getClass());
        }
    }

    @Test
    void whenGetByUuidThrowsAUuidParseException(){
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(clientModel));
        try {
            clientService.getByUuid(WRONG_UUID);
        }catch (UuidParseException ex){
            assertEquals(UuidParseException.class, ex.getClass());
        }
    }

    @Test
    void whenGetAllReturnsAList(){
        when(clientRepository.findAll()).thenReturn(clientsList);
        List<ClientModel> resultList = clientService.getAll();
        assertNotNull(resultList);
        assertEquals(ClientModel.class, resultList.get(0).getClass());
    }

    @Test
    void whenGetClientByDocumentReturnsAClient(){
        when(clientRepository.findByDocument(anyString())).thenReturn(clientModel);
        ClientModel result = clientService.getClientByDocument(CLIENT_DOCUMENT);
        assertNotNull(result);
        assertEquals(ClientModel.class, result.getClass());
    }

    @Test
    void whenInsertReturnsAClient(){
        when(clientRepository.save(any(ClientModel.class))).thenReturn(clientModel);
        ClientModel result = clientService.insertClient(clientModel);
        assertNotNull(result);
        assertEquals(ClientModel.class, result.getClass());
    }

    @Test
    void whenUpdateReturnsAClient(){
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(clientModel));
        when(clientRepository.save(any(ClientModel.class))).thenReturn(clientModel);
        ClientModel result = clientService.updateClient(CLIENT_UUID.toString(), clientModel);
        assertNotNull(result.getId());
        assertEquals(ClientModel.class, result.getClass());
    }

    @Test
    void whenUpdateReturnsAUuidParseException(){
        when(clientRepository.save(any(ClientModel.class))).thenReturn(clientModel);
        try {
            clientService.updateClient(WRONG_UUID, clientModel);
        }catch (UuidParseException ex){
            assertEquals(UuidParseException.class, ex.getClass());
        }
    }

    @Test
    void whenDeleteReturnsOk(){
        doNothing().when(clientRepository).deleteById(any(UUID.class));
        clientService.deleteClient(CLIENT_UUID.toString());
        verify(clientRepository, times(1)).deleteById(CLIENT_UUID);
    }

    @Test
    void whenDeleteThrowsUuidException(){
        doNothing().when(clientRepository).deleteById(any(UUID.class));
        try {
            clientService.deleteClient(WRONG_UUID);
        }catch (UuidParseException ex){
            assertEquals(UuidParseException.class, ex.getClass());
        }
    }

    private void startClient(){
        clientModel = new ClientModel();
    }

    private void startListClients(){
        clientsList = new ArrayList<>();
        clientsList.add(new ClientModel());
        clientsList.add(new ClientModel());
    }
}
