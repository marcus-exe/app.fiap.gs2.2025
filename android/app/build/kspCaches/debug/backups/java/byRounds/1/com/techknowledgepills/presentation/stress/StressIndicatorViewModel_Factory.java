package com.techknowledgepills.presentation.stress;

import com.techknowledgepills.data.repository.StressIndicatorRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class StressIndicatorViewModel_Factory implements Factory<StressIndicatorViewModel> {
  private final Provider<StressIndicatorRepository> stressIndicatorRepositoryProvider;

  public StressIndicatorViewModel_Factory(
      Provider<StressIndicatorRepository> stressIndicatorRepositoryProvider) {
    this.stressIndicatorRepositoryProvider = stressIndicatorRepositoryProvider;
  }

  @Override
  public StressIndicatorViewModel get() {
    return newInstance(stressIndicatorRepositoryProvider.get());
  }

  public static StressIndicatorViewModel_Factory create(
      Provider<StressIndicatorRepository> stressIndicatorRepositoryProvider) {
    return new StressIndicatorViewModel_Factory(stressIndicatorRepositoryProvider);
  }

  public static StressIndicatorViewModel newInstance(
      StressIndicatorRepository stressIndicatorRepository) {
    return new StressIndicatorViewModel(stressIndicatorRepository);
  }
}
