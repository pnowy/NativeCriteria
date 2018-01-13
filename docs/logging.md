# Logging

###### Logger to log execution sql time \(available on DEBUG level\). You could always enable by \(the root appender will be used\)

```xml
<logger name="NativeCriteriaPerformance" level="DEBUG" />
```

###### If you want add additional log output \(system log or separate file\) configure the appender too:

```xml
<logger name="NativeCriteriaPerformance" level="DEBUG">
   <appender-ref ref="stdout" />
</logger>
```



