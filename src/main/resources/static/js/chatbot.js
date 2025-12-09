// chatbot.js - defensive + streaming-compatible
document.addEventListener('DOMContentLoaded', () => {
  const icon = document.getElementById('chatbot-icon');
  const win  = document.getElementById('chatbot-window');
  const closeBtn = document.getElementById('close-chat');
  const sendBtn  = document.getElementById('send-btn');
  const input    = document.getElementById('chat-input');
  const body     = document.getElementById('chat-body');

  console.log('chatbot.js loaded', { icon: !!icon, window: !!win, sendBtn: !!sendBtn });

  if (!icon || !win) {
    console.error('Chatbot missing required elements (icon or window).');
    return;
  }

  // initialize hidden
  win.classList.add('hidden');
  win.style.display = 'none';
  win.setAttribute('aria-hidden', 'true');

  function openChat() {
    win.classList.remove('hidden');
    win.classList.add('show');
    win.style.display = 'flex';
    win.setAttribute('aria-hidden', 'false');
    icon.style.display = 'none';
    setTimeout(() => input && input.focus(), 80);
  }

  function closeChat() {
    win.classList.add('hidden');
    win.classList.remove('show');
    win.style.display = 'none';
    win.setAttribute('aria-hidden', 'true');
    icon.style.display = 'flex';
  }

  icon.addEventListener('click', () => {
    console.log('chatbot icon clicked');
    openChat();
  });

  if (closeBtn) closeBtn.addEventListener('click', () => {
    console.log('chatbot close clicked');
    closeChat();
  });

  document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape' && win && window.getComputedStyle(win).display !== 'none') {
      closeChat();
    }
  });

  function appendBot(text) {
    const d = document.createElement('div');
    d.className = 'bot-message';
    d.textContent = text;
    body.appendChild(d);
    body.scrollTop = body.scrollHeight;
    return d;
  }

  function appendUser(text) {
    const d = document.createElement('div');
    d.className = 'user-message';
    d.textContent = text;
    body.appendChild(d);
    body.scrollTop = body.scrollHeight;
  }

  async function sendMessage() {
    const msg = (input && input.value || '').trim();
    if (!msg) return;
    appendUser(msg);
    input.value = '';

    const botElem = appendBot('🤖 Thinking...');

    try {
      const res = await fetch('/chatbot/stream', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ message: msg })
      });

      if (!res.ok) {
        throw new Error('Network response not OK: ' + res.status);
      }

      if (!res.body) {
        // No streaming support from server: parse full json
        const json = await res.json();
        botElem.textContent = json.response || 'No response';
        return;
      }

      // streaming reader
      const reader = res.body.getReader();
      const decoder = new TextDecoder();
      botElem.textContent = ''; // clear placeholder

      while (true) {
        const { done, value } = await reader.read();
        if (done) break;
        const chunk = decoder.decode(value, { stream: true });
        botElem.textContent += chunk;
        body.scrollTop = body.scrollHeight;
      }
    } catch (err) {
      console.error('chatbot stream error', err);
      botElem.textContent = '⚠️ Could not reach the chatbot. Try again later.';
    }
  }

  if (sendBtn) sendBtn.addEventListener('click', sendMessage);
  if (input) input.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') sendMessage();
  });
});
