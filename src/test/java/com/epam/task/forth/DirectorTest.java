package com.epam.task.forth;

import com.epam.task.forth.entities.Drops;
import com.epam.task.forth.entities.Medicine;
import com.epam.task.forth.entities.MedicineGroup;
import com.epam.task.forth.entities.Pills;
import com.epam.task.forth.parsing.Parser;
import com.epam.task.forth.parsing.ParserException;
import com.epam.task.forth.validation.ValidatorException;
import com.epam.task.forth.validation.XmlValidator;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class DirectorTest {

    private final static String FILE = "";
    private final static Medicine FIRST_MEDICINE =
            new Pills("C", "A", MedicineGroup.BUD, 1, 1, "0");
    private final static Medicine SECOND_MEDICINE =
            new Drops("B", "Name", MedicineGroup.BUD, 5, 1, "1");
    private final static Medicine THIRD_MEDICINE =
            new Drops("A", "B", MedicineGroup.ANTIBIOTIC, 5, 5, "2");
    private Director director;

    @Test
    public void testParseShouldReturnEmptyListWhenItNothingToParse() throws ValidatorException, ParserException {
        //given
        XmlValidator validator = Mockito.mock(XmlValidator.class);
        when(validator.isValid(anyString())).thenReturn(false);
        Parser parser = Mockito.mock(Parser.class);

        director = new Director(validator, parser);
        List<Medicine> expected = new ArrayList<>();

        //when
        List<Medicine> actual = director.parse(FILE);
        //then
        Assert.assertEquals(actual, expected);

    }

    @Test
    public void testParseShouldReturnListWhenMultipleFilesToBeParsed() throws ValidatorException, ParserException {
        //given
        XmlValidator validator = Mockito.mock(XmlValidator.class);
        when(validator.isValid(anyString())).thenReturn(true);
        Parser parser = Mockito.mock(Parser.class);
        List<Medicine> resultList = Arrays.asList(FIRST_MEDICINE, SECOND_MEDICINE, THIRD_MEDICINE);
        when(parser.parse(anyObject())).thenReturn(resultList);

        director = new Director(validator, parser);

        //when
        List<Medicine> actual = director.parse(FILE);
        //then
        Assert.assertEquals(actual, resultList);

    }

}
