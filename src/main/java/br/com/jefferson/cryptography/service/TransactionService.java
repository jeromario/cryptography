package br.com.jefferson.cryptography.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.jefferson.cryptography.controller.dto.CreateTransactionRequest;
import br.com.jefferson.cryptography.controller.dto.TransactionResponse;
import br.com.jefferson.cryptography.controller.dto.UpdateTransactionRequest;
import br.com.jefferson.cryptography.entity.TransactionEntity;
import br.com.jefferson.cryptography.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void create(CreateTransactionRequest request) {
        var entity = new TransactionEntity();
        entity.setRawUserDocument(request.userDocument());
        entity.setRawCreditCardToken(request.creditCardToken());
        entity.setTransactionValue(request.value());

        transactionRepository.save(entity);
    }

    public Page<TransactionResponse> listAll(int page, int pageSize) {
        var content = transactionRepository.findAll(PageRequest.of(page, pageSize));

        return content.map(transactionEntity -> TransactionResponse.fromEntity(transactionEntity));
    }

    public TransactionResponse findById(Long id) {
        var entity = getById(id);
        return TransactionResponse.fromEntity(entity);
    }

    public void update(Long id, UpdateTransactionRequest request) {
        var entity = getById(id);
        
        entity.setTransactionValue(request.value());

        transactionRepository.save(entity);


    }


    public void delete(Long id) {
       var entity = getById(id);
        
        transactionRepository.delete(entity);
    }

    private TransactionEntity getById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
