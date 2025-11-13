package com.techknowledgepills.presentation.home;

import com.techknowledgepills.data.repository.RecommendationRepository;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<StressIndicatorRepository> stressIndicatorRepositoryProvider;

  private final Provider<RecommendationRepository> recommendationRepositoryProvider;

  public HomeViewModel_Factory(
      Provider<StressIndicatorRepository> stressIndicatorRepositoryProvider,
      Provider<RecommendationRepository> recommendationRepositoryProvider) {
    this.stressIndicatorRepositoryProvider = stressIndicatorRepositoryProvider;
    this.recommendationRepositoryProvider = recommendationRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(stressIndicatorRepositoryProvider.get(), recommendationRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<StressIndicatorRepository> stressIndicatorRepositoryProvider,
      Provider<RecommendationRepository> recommendationRepositoryProvider) {
    return new HomeViewModel_Factory(stressIndicatorRepositoryProvider, recommendationRepositoryProvider);
  }

  public static HomeViewModel newInstance(StressIndicatorRepository stressIndicatorRepository,
      RecommendationRepository recommendationRepository) {
    return new HomeViewModel(stressIndicatorRepository, recommendationRepository);
  }
}
