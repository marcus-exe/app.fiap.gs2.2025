package com.techknowledgepills.presentation.recommendation;

import com.techknowledgepills.data.repository.RecommendationRepository;
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
public final class RecommendationViewModel_Factory implements Factory<RecommendationViewModel> {
  private final Provider<RecommendationRepository> recommendationRepositoryProvider;

  public RecommendationViewModel_Factory(
      Provider<RecommendationRepository> recommendationRepositoryProvider) {
    this.recommendationRepositoryProvider = recommendationRepositoryProvider;
  }

  @Override
  public RecommendationViewModel get() {
    return newInstance(recommendationRepositoryProvider.get());
  }

  public static RecommendationViewModel_Factory create(
      Provider<RecommendationRepository> recommendationRepositoryProvider) {
    return new RecommendationViewModel_Factory(recommendationRepositoryProvider);
  }

  public static RecommendationViewModel newInstance(
      RecommendationRepository recommendationRepository) {
    return new RecommendationViewModel(recommendationRepository);
  }
}
