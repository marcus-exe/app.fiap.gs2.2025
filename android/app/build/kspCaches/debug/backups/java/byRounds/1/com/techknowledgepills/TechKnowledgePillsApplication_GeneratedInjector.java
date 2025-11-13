package com.techknowledgepills;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;
import javax.annotation.processing.Generated;

@OriginatingElement(
    topLevelClass = TechKnowledgePillsApplication.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
@Generated("dagger.hilt.android.processor.internal.androidentrypoint.InjectorEntryPointGenerator")
public interface TechKnowledgePillsApplication_GeneratedInjector {
  void injectTechKnowledgePillsApplication(
      TechKnowledgePillsApplication techKnowledgePillsApplication);
}
