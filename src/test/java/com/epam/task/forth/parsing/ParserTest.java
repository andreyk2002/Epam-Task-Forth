package com.epam.task.forth.parsing;

import com.epam.task.forth.entities.Drops;
import com.epam.task.forth.entities.Medicine;
import com.epam.task.forth.entities.MedicineGroup;
import com.epam.task.forth.entities.Pills;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class ParserTest {


    private final static String SINGLE_ELEMENT_FILE = "src/test/resources/valid_single.xml";
    private final static String MULTI_ELEMENTS_FILE = "src/test/resources/valid_multiple.xml";

    private final static Medicine FIRST_MEDICINE = new Pills("BelFarm", "Aevit", MedicineGroup.ANTIBIOTIC, 1, 1, "ID-0");
    private final static Medicine SECOND_MEDICINE = new Drops("BelFarm", "Hormones", MedicineGroup.BUD, 5.0, 50, "ID-1");
    private final static Medicine THIRD_MEDICINE = new Drops("GermFarm", "Some drops", MedicineGroup.ANTIBIOTIC, 5.0, 50, "ID-2");

    private final Parser parser = getParser();

    protected abstract Parser getParser();

    @Test
    public void testParseShouldParseValidSingleElementFile() throws ParserException {
        //given
        List<Medicine> expected = Collections.singletonList(FIRST_MEDICINE);

        //when
        List<Medicine> result = parser.parse(SINGLE_ELEMENT_FILE);

        //then
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testParseShouldParseValidMultiElementFile() throws ParserException {
        //given
        List<Medicine> expected = Arrays.asList(FIRST_MEDICINE, SECOND_MEDICINE, THIRD_MEDICINE);

        //when
        List<Medicine> result = parser.parse(MULTI_ELEMENTS_FILE);

        //then
        Assert.assertTrue(expected.size() == result.size() && expected.containsAll(result));

    }
}
