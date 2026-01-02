# Script para executar o backend Spring Boot
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Iniciando Backend Sentiment API" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Verifica se está no diretório correto
if (-not (Test-Path "pom.xml")) {
    Write-Host "ERRO: pom.xml nao encontrado!" -ForegroundColor Red
    Write-Host "Certifique-se de estar no diretorio do projeto (sentimentAPI)" -ForegroundColor Yellow
    exit 1
}

# Verifica se o Maven Wrapper existe
if (-not (Test-Path "mvnw.cmd")) {
    Write-Host "ERRO: Maven Wrapper (mvnw.cmd) nao encontrado!" -ForegroundColor Red
    exit 1
}

# Verifica se a porta 8080 está em uso e libera se necessário
Write-Host "Verificando porta 8080..." -ForegroundColor Yellow
$portInUse = netstat -ano | findstr :8080
if ($portInUse) {
    Write-Host "Porta 8080 esta em uso. Tentando liberar..." -ForegroundColor Yellow
    $processes = netstat -ano | findstr :8080 | ForEach-Object {
        if ($_ -match '\s+(\d+)$') {
            $matches[1]
        }
    } | Select-Object -Unique
    
    foreach ($pid in $processes) {
        $process = Get-Process -Id $pid -ErrorAction SilentlyContinue
        if ($process -and $process.ProcessName -eq "java") {
            Write-Host "Encerrando processo Java (PID: $pid)..." -ForegroundColor Yellow
            Stop-Process -Id $pid -Force -ErrorAction SilentlyContinue
            Start-Sleep -Seconds 2
        }
    }
    Write-Host "Porta 8080 liberada!" -ForegroundColor Green
    Write-Host ""
} else {
    Write-Host "Porta 8080 disponivel." -ForegroundColor Green
    Write-Host ""
}

Write-Host "Executando Maven Wrapper..." -ForegroundColor Green
Write-Host ""

# Executa o Spring Boot
.\mvnw.cmd spring-boot:run

