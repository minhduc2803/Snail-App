package vn.zalopay.ducnm8.utils;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import lombok.Builder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Builder
public class Tracker {
  private static final MeterRegistry METER_REGISTRY =
      new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
  private static final Map<String, Timer> METRICS = new ConcurrentHashMap<>();

  public static void initialize(String applicationName) {
    METER_REGISTRY.config().commonTags("application", applicationName);
  }

  private long startTime = 0;
  private String metricName;
  @Builder.Default private String step = "";
  @Builder.Default private String code = "";

  public static MeterRegistry getMeterRegistry() {
    return METER_REGISTRY;
  }

  public void record() {
    METRICS
        .computeIfAbsent(
            getKey(metricName, step, code),
            k ->
                Timer.builder(metricName)
                    .tag("step", step)
                    .tag("code", code)
                    .publishPercentileHistogram()
                    .register(METER_REGISTRY))
        .record(System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);
  }

  private static String getKey(String method, String step, String code) {
    return method + "|" + step + "|" + code;
  }
}
