package com.github.fhtw.swp.tutorium.e2eTest.support;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

@XmlRootElement(name = "testsuite")
@XmlAccessorType(XmlAccessType.FIELD)
public class JUnitResult {

    public static JUnitResult fromFile(File junitResultFile) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(JUnitResult.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (JUnitResult) unmarshaller.unmarshal(junitResultFile);
    }

    @XmlAttribute(name = "failures")
    private int failureCount;

    @XmlAttribute(name = "skipped")
    private int skippedCount;

    @XmlAttribute(name = "tests")
    private int totalTestCount;

    public int getFailureCount() {
        return failureCount;
    }

    public int getSkippedCount() {
        return skippedCount;
    }

    public int getTotalTestCount() {
        return totalTestCount;
    }
}
