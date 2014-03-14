## 1.注释 ##

@Provider

## 2.载入和注册 ##
jersey-commmon依赖HK2(轻量级DI架构)

hk2-locator

- javax.inject
- asm-all-repackaged
- cglib


ReflectionHelper.makeMe(Constructor<?>, Object[]) line: 1092	


Providers.getAllRankedProviders(ServiceLocator, Class<T>) line: 234	
providerMap.put(key, new RankedProvider<T>(provider.getService(), key.getRanking()));


## 3.AOP ##


ServerRuntime$Responder.processResponse(ContainerResponse) line: 360	
