package com.endorocket.hexagonalapp.architecture;

import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.jupiter.api.Test;

class DependenciesTest {
  @Test
  void shouldHaveNoCycles() {
    SlicesRuleDefinition.slices()
        .matching("com.endorocket.hexagonalapp(*)..")
        .should().beFreeOfCycles();
  }
}
