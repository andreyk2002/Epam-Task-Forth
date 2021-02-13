package com.epam.task.forth.validation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class XmlValidatorTest {

    private static final String EMPTY_FILE = "src/test/resources/empty.xml";
    private static final String CORRECT_FILE = "src/test/resources/valid_multiple.xml";
    private static final String MISSED_ATTRIBUTES_FILE = "src/test/resources/missed_attribute.xml";
    private static final String MISSED_TAGS_FILE = "src/test/resources/missed_tags.xml";
    private static final String WRONG_TAGS_ORDER_FILE = "src/test/resources/wrong_order.xml";
    private static final String WRONG_TAGS_VALUES_FILE = "src/test/resources/wrong_tags.xml";
    private static final String WRONG_ATTRIBUTES_VALUES_FILE = "src/test/resources/wrong_attributes.xml";

    private final XmlValidator validator = new XmlValidator("src/main/resources/Medicines.xsd");

    @Test
    public void testValidateShouldValidateWhenFileIsCorrect() throws ValidatorException {
        //when
        boolean result = validator.isValid(CORRECT_FILE);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void testValidateShouldNotValidateWhenFileIsEmpty() throws ValidatorException {
        //when
        boolean result = validator.isValid(EMPTY_FILE);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void testValidateShouldNotValidateWhenRequiredAttributesAreMissed() throws ValidatorException {
        //when
        boolean result = validator.isValid(MISSED_ATTRIBUTES_FILE);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void testValidateShouldNotValidateWhenTagsAreMissed() throws ValidatorException {
        //when
        boolean result = validator.isValid(MISSED_TAGS_FILE);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void testValidateShouldNotValidateWhenTagsOrderAreWrong() throws ValidatorException {
        //when
        boolean result = validator.isValid(WRONG_TAGS_ORDER_FILE);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void testValidateShouldNotValidateWhenTagsValuesAreWrong() throws ValidatorException {
        //when
        boolean result = validator.isValid(WRONG_TAGS_VALUES_FILE);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void testValidateShouldNotValidateWhenAttributesValuesAreWrong() throws ValidatorException {
        //when
        boolean result = validator.isValid(WRONG_ATTRIBUTES_VALUES_FILE);
        //then
        Assert.assertFalse(result);
    }
}
