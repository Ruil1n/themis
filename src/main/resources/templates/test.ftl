<#list nameList as name>
List<${name}Vo> get${name}ByUserIdAndTerm(String stuId, String term, Integer page, Integer size);

</#list>
