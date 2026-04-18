package com.oussma.order.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.oussma.order.entity.Sequence;

import org.springframework.data.mongodb.core.FindAndModifyOptions;

class SequenceGeneratoTest {

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private SequenceGenerator sequenceGenerator;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateNextOrderId_shouldReturnIncrementedValue() {

        // Arrange
        Sequence mockSequence = new Sequence();
        mockSequence.setId("sequence");
        mockSequence.setOrderSequence(10);

        when(mongoOperations.findAndModify(
                any(Query.class),
                any(Update.class),
                any(FindAndModifyOptions.class),
                eq(Sequence.class)
        )).thenReturn(mockSequence);

        // Act
        int result = sequenceGenerator.generateNextOrderId();

        // Assert
        assertEquals(10, result);

        verify(mongoOperations, times(1))
                .findAndModify(any(Query.class), any(Update.class), any(FindAndModifyOptions.class), eq(Sequence.class));
    }
}