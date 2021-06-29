package com.endorocket.hexagonalapp.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@Tag("ArchitectureTest")
class PackageStructureTest {
  private static final String JAVA = "java..";
  private static final String DOMAIN = "..domain..";
  private static final String APPLICATION = "..application..";
  private static final String QUERY = "..query..";
  private static final String INFRASTRUCTURE = "..infrastructure..";

  public final JavaClasses javaClasses = RentalApplicationClasses.get();

  @Test
  void domainShouldTalkOnlyWithDomain() {
    classes().that().resideInAPackage(DOMAIN)
        .should().onlyAccessClassesThat().resideInAnyPackage(DOMAIN, JAVA)
        .check(javaClasses);
  }

  @Test
  void applicationShouldTalkOnlyWithDomainAndApplication() {
    classes().that().resideInAPackage(APPLICATION)
        .should().onlyAccessClassesThat().resideInAnyPackage(DOMAIN, APPLICATION, JAVA)
        .check(javaClasses);
  }

  @Test
  void queryShouldTalkOnlyWithQuery() {
    classes().that().resideInAPackage(QUERY)
        .should().onlyAccessClassesThat().resideInAnyPackage(QUERY, JAVA)
        .check(javaClasses);
  }

  @Test
  void infrastructureShouldNotTalkWithDomain() {
    classes().that().resideInAPackage(INFRASTRUCTURE)
        .should().onlyAccessClassesThat().resideOutsideOfPackage(DOMAIN)
        .orShould().haveSimpleNameContaining("Repository")
        .check(javaClasses);
  }
}
